package com.volkswagen.techchallenge.application.command.handler

import com.volkswagen.common.cqrs.command.CommandHandler
import com.volkswagen.techchallenge.application.command.CreateWorkspaceCommand
import com.volkswagen.techchallenge.domain.entity.Workspace
import com.volkswagen.techchallenge.domain.respository.WorkspaceRepository
import com.volkswagen.techchallenge.metrics.MetricNames
import com.volkswagen.techchallenge.metrics.MetricsPublisher
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CreateWorkspaceCommandHandler(
    val metricsPublisher: MetricsPublisher,
    val workspaceRepository: WorkspaceRepository
) : CommandHandler<CreateWorkspaceCommand> {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun handle(command: CreateWorkspaceCommand) {

        workspaceRepository.create(
            Workspace(
                logicalId = command.workspaceLogicalId,
                upperRightCornerX = command.upperRightCornerX,
                upperRightCornerY = command.upperRightCornerY
            )
        )

        logger.info("[${this.javaClass.simpleName}] - Done!")
        metricsPublisher.increment(MetricNames.WORKSPACE_CREATED)
    }
}