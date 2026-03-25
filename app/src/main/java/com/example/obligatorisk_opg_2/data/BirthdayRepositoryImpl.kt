package com.example.obligatorisk_opg_2.data

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class BirthdayRepositoryImpl(
    private val birthdatAPI: BirthdaysAPI,
    private val dispatcher: CoroutineDispatcher
) : BirthdayRepository {
    override suspend fun getBirthdays(): NetworkResult<List<Birthday>> {
        return withContext(dispatcher) {
            try {
                val response = birthdatAPI.getBirthdays()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        NetworkResult.Success(body)
                    } else {
                        NetworkResult.Error("Response body is null")
                    }
                } else {
                    NetworkResult.Error(response.message())
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun getBirthdays(userId: String): NetworkResult<List<Birthday>> {
        return withContext(dispatcher) {
            try {
                val response = birthdatAPI.getBirthdays(userId)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        NetworkResult.Success(body)
                    } else {
                        NetworkResult.Error("Response body is null")
                    }
                } else {
                    NetworkResult.Error(response.message())
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }


    override suspend fun deleteBirthday(birthdayId: Int): NetworkResult<Birthday> {
        return withContext(dispatcher) {
            try {
                val response = birthdatAPI.deleteBirthday(birthdayId)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        NetworkResult.Success(body)
                    } else {
                        NetworkResult.Error("Response body is null")
                    }
                } else {
                    NetworkResult.Error(response.message())
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun addBirthday(birthday: Birthday): NetworkResult<Birthday> {
        return withContext(dispatcher) {
            try {
                val response = birthdatAPI.addBirthday(birthday)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        NetworkResult.Success(body)
                    } else {
                        NetworkResult.Error("Response body is null")
                    }
                } else {
                    NetworkResult.Error(response.message())
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun updateBirthday(birthdayId: Int, birthday: Birthday): NetworkResult<Birthday> {
        return withContext(dispatcher) {
            try {
                val response = birthdatAPI.updateBirthday(birthdayId, birthday)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        NetworkResult.Success(body)
                    } else {
                        NetworkResult.Error("Response body is null")
                    }
                } else {
                    NetworkResult.Error(response.message())
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error")
            }
        }
    }
}