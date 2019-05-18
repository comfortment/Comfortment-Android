package com.comfortment.data.repository

import com.comfortment.data.model.mai.MAIEntityMapper
import com.comfortment.data.model.mai.MyMAIEntityMapper
import com.comfortment.data.source.mai.MAILocalDataSource
import com.comfortment.data.source.mai.MAIRemoteDataSource
import com.comfortment.domain.model.MAI
import com.comfortment.domain.model.MyMAI
import com.comfortment.domain.repository.mai.MAIRepository
import com.comfortment.domain.repository.mai.MyMAIRepository
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import javax.inject.Inject

class MAIRepositoryImp @Inject constructor(
    private val maiRemoteDataSource: MAIRemoteDataSource,
    private val maiLocalDataSource: MAILocalDataSource,
    private val maiEntityMapper: MAIEntityMapper,
    private val myMAIEntityMapper: MyMAIEntityMapper
) : MAIRepository, MyMAIRepository {

    override fun loadAI(id: String): Single<MAI> =
        maiRemoteDataSource.loadMyAI(id)
            .map { data ->
                saveAI(MyMAI(data.id, data.buildingNumber, data.roomNumber))
                data
            }
            .doOnError { Throwable("Not found!") }

    override fun getBuildingAI(buildingNumber: Int): Single<List<MAI>> =
        maiRemoteDataSource.buildingAI(buildingNumber)


    override fun getFloorAI(buildingNumber: Int, floor: Int): Single<List<MAI>> =
        maiRemoteDataSource.floorAI(buildingNumber, floor)

    override fun registerAI(mai: MAI): Observable<Response<Any>> =
        maiRemoteDataSource.registerAI(maiEntityMapper.mapToEntity(mai))

    override fun bringAI(): Maybe<MyMAI> =
        maiLocalDataSource.bringMyMAI()
            .map { data -> myMAIEntityMapper.mapToDomain(data) }
            .doOnError { Throwable("Not found!") }

    override fun saveAI(myMAI: MyMAI) = maiLocalDataSource.saveMyMAI(myMAIEntityMapper.mapToEntity(myMAI))
}