package caio.com.domain.usecase

import caio.com.domain.FlowableUseCase
import caio.com.domain.PostExecutionThread
import caio.com.domain.ThreadExecutor
import caio.com.domain.model.Omdb
import caio.com.domain.model.Response
import caio.com.domain.repository.OmdbRepository
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Use case used for retreiving a [List] of [Omdb] instances from the [OmdbRepository]
 */
open class GetOmdb @Inject constructor(
    val repository: OmdbRepository,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) :
    FlowableUseCase<Response, String?>(threadExecutor, postExecutionThread) {

    public override fun buildUseCaseObservable(params: String?): Flowable<Response> {
        return repository.get(params)
    }
}