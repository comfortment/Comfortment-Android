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

    override fun loadAI(accessToken: String, id: String): Single<MAI> =
        maiRemoteDataSource.loadMyAI(accessToken, id)
            .map { data ->
                saveAI(MyMAI(data.id, data.buildingNumber, data.roomNumber))
                maiEntityMapper.mapToDomain(data)
            }
            .doOnError { Throwable("Not found!") }

    override fun getBuildingAI(accessToken: String, buildingNumber: Int): Single<List<MAI>> =
        maiRemoteDataSource.buildingAI(accessToken, buildingNumber)
            .map {
                val maiList = ArrayList<MAI>()
                it.forEach { maiList.add(maiEntityMapper.mapToDomain(it)) }
                maiList
            }


    override fun getFloorAI(accessToken: String, buildingNumber: Int, floor: Int): Single<List<MAI>> =
        maiRemoteDataSource.floorAI(accessToken, buildingNumber, floor)
            .map { data ->
                val maiList = ArrayList<MAI>()
                data.forEach { maiList.add(maiEntityMapper.mapToDomain(it)) }
                maiList
            }

    override fun registerAI(accessToken: String, mai: MAI): Observable<Response<Unit>> =
        maiRemoteDataSource.registerAI(accessToken, mai)

    override fun bringAI(): Maybe<MyMAI> =
        maiLocalDataSource.bringMyMAI()
            .map { data -> myMAIEntityMapper.mapToDomain(data) }
            .doOnError { Throwable("Not found!") }

    override fun saveAI(myMAI: MyMAI) = maiLocalDataSource.saveMyMAI(myMAIEntityMapper.mapToEntity(myMAI))
}