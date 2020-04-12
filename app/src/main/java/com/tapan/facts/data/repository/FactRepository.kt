package com.tapan.facts.data.repository

import com.tapan.facts.data.core.BaseRepository
import com.tapan.facts.data.core.Either
import com.tapan.facts.data.core.MyException
import com.tapan.facts.data.models.FactsRS
import retrofit2.http.GET

interface FactRepository {
    suspend fun getFacts(): Either<MyException, FactsRS>
}

class FactRepositoryImpl(private val factRemoteSource: FactRemoteSource) : BaseRepository(),
    FactRepository {
    override suspend fun getFacts() = executeSafeApiCall {
        factRemoteSource.getFactsFromRemote()
    }
}

public  abstract class FactRemoteSource {
    abstract suspend fun getFactsFromRemote(): FactsRS
}

class FactRemoteSourceImpl(val factNetworkService: FactNetworkService) : FactRemoteSource() {
    override suspend fun getFactsFromRemote(): FactsRS {
        return factNetworkService.getFacts()
    }

}

interface FactNetworkService {
    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun getFacts(): FactsRS
}
