package com.volkswagen.techchallenge.application.command

import com.volkswagen.common.cqrs.command.Command
import java.util.UUID

class CreateWorkspaceCommand(
    val workspaceLogicalId: UUID,
    val upperRightCornerX: Int,
    val upperRightCornerY: Int
) : Command