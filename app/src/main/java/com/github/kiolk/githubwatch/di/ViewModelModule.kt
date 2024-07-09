package com.github.kiolk.githubwatch.di

import com.github.kiolk.githubwatch.presentation.screens.chart.ChartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ChartViewModel(get()) }
}
