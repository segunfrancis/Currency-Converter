package com.project.segunfrancis.currencyconverter.mapper

/**
 * Created by SegunFrancis
 */

interface Mapper<I, O> {
    fun mapDomainToApp(data: I): O
}