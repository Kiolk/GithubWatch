package com.github.kiolk.githubwatch.data.statistics.datasource

import com.apollographql.apollo3.ApolloClient
import com.github.kiolk.githubwatch.UserQuery
import com.github.kiolk.githubwatch.data.statistics.models.ChartDataModel

class RemoteStatisticsDataSource(
    private val graphqlClient: ApolloClient
) : StatisticsDataSource {
    override suspend fun getUserStatistics(userName: String): ChartDataModel {
        val userResponse = graphqlClient.query(UserQuery(userName)).execute()

        return ChartDataModel(userResponse.data?.user)
    }
}
