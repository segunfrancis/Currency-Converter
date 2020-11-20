package com.project.segunfrancis.data.datasource.local.mapper

/**
 * Created by SegunFrancis
 */

interface Mapper<L, D> {
    fun mapLocalToDomain(data: L): D
    fun mapDomainToLocal(data: D): L
}