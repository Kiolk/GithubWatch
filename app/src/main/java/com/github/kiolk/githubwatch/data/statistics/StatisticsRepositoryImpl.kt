package com.github.kiolk.githubwatch.data.statistics

import com.github.kiolk.githubwatch.UserQuery
import com.github.kiolk.githubwatch.data.statistics.datasource.StatisticsDataSource
import com.github.kiolk.githubwatch.data.statistics.models.ChartDataModel
import com.github.kiolk.githubwatch.domain.models.ChartData
import com.github.kiolk.githubwatch.domain.models.DayStatistic
import com.github.kiolk.githubwatch.domain.models.WeekStatistic
import kotlinx.datetime.LocalDate

class StatisticsRepositoryImpl(private val statisticsDataSource: StatisticsDataSource) :
    StatisticsRepository {
    override suspend fun getUserStatistics(userName: String): ChartData {
        return statisticsDataSource.getUserStatistics(userName).mapToData().splitByMonths()
    }
}

private fun ChartDataModel.mapToData(): ChartData {
    return ChartData(weeks = (this.data?.contributionsCollection?.contributionCalendar?.weeks?.map {
        it.mapToWeekStatistic()
    }.orEmpty()))
}

private fun UserQuery.Week.mapToWeekStatistic(): WeekStatistic {
    return WeekStatistic(
        contributionDays = this.contributionDays.map {
            DayStatistic(
                weekDay = it.weekday,
                contributionCount = it.contributionCount,
                contributionLevel = it.contributionLevel.name,
                date = LocalDate.parse(it.date.toString()),
                color = it.color
            )
        }, firstDay = LocalDate.parse(this.firstDay.toString())
    )
}

private fun ChartData.splitByMonths(): ChartData {
    val weeks = mutableListOf<WeekStatistic>()

    this.weeks.forEach {
        if (it.isBorderWeek()) {
            val (endWeek, startWeek) = it.splitByWeeks()

            weeks.add(endWeek)
            weeks.add(startWeek)
        } else {
            weeks.add(it)
        }
    }

    return ChartData(weeks = weeks)
}

fun WeekStatistic.splitByWeeks(): Pair<WeekStatistic, WeekStatistic> {
    val firstDay = this.contributionDays.first()
    val endWeek = WeekStatistic(
        firstDay = firstDay.date,
        contributionDays = this.contributionDays.filter { it.date.monthNumber == firstDay.date.monthNumber })
    val startWeek =
        WeekStatistic(firstDay = this.contributionDays.first { it.date.monthNumber != firstDay.date.monthNumber }.date,
            contributionDays = this.contributionDays.filter { it.date.monthNumber != firstDay.date.monthNumber })
    return Pair(endWeek, startWeek)
}

fun WeekStatistic.isBorderWeek(): Boolean {
    val firstDay = this.contributionDays.firstOrNull() ?: return false
    val isBorderWeek =
        this.contributionDays.any { firstDay.date.monthNumber != it.date.monthNumber }

    return isBorderWeek
}
