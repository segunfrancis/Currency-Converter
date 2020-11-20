package com.project.segunfrancis.data.datasource.remote.mapper

/**
 * Created by SegunFrancis
 */

interface Mapper<R, D> {
    fun mapRemoteToDomain(data: R): D
}