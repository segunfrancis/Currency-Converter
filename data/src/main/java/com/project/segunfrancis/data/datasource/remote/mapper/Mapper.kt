package com.project.segunfrancis.data.datasource.remote.mapper

/**
 * Created by SegunFrancis
 */
interface Mapper<R, D> {
    fun mapLocalToDomain(data: R): D
}