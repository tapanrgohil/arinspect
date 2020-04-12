package com.tapan.facts.data.repository

import com.tapan.facts.data.core.BaseRepository
import com.tapan.facts.data.core.Either
import com.tapan.facts.data.core.MyException
import com.tapan.facts.data.models.FactsRS

interface FactRepository {
    suspend fun getFacts(): Either<MyException, FactsRS>
}

class FactRepositoryImpl:BaseRepository(),FactRepository{
    override suspend fun getFacts(): Either<MyException, FactsRS> {
        TODO()
    }

}
