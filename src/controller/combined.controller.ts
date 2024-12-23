import {
  Controller,
  Get,
  Post,
  Delete,
  Param,
  Body,
  HttpCode,
  HttpStatus,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { ChoreService } from '../service/chore.service';
import { Subtask } from '../model/subtask.entity';
import { SubtaskDTO } from './dto/outgoing/subtask.dto';
import { NewSubtaskDTO } from './dto/incoming/newSubtask.dto';
import { ChoreDTO } from './dto/outgoing/chore.dto';

@Controller('chores/:choreId/subtasks')
export class SubtaskController {
  constructor(private readonly choreService: ChoreService) {}

  @Get()
  async findAll(@Param('choreId') choreId: number): Promise<SubtaskDTO[]> {
    const chore = await this.choreService.findById(choreId);
    return chore.subtasks;
  }

  @Post()
  @HttpCode(HttpStatus.CREATED)
  @UsePipes(new ValidationPipe({ transform: true }))
  async create(
    @Param('choreId') choreId: number,
    @Body() subtaskData: NewSubtaskDTO
  ): Promise<ChoreDTO> {
    const chore = await this.choreService.findById(choreId);
    const subtask = { ...subtaskData, chore } as Subtask;
    chore.subtasks.push(subtask);
    await this.choreService.update(choreId, chore);
    return chore;
  }

  @Delete(':subtaskId')
  @HttpCode(HttpStatus.NO_CONTENT)
  async delete(
    @Param('choreId') choreId: number,
    @Param('subtaskId') subtaskId: number
  ): Promise<void> {
    const chore = await this.choreService.findById(choreId);
    chore.subtasks = chore.subtasks.filter(subtask => subtask.id !== subtaskId);
    await this.choreService.update(choreId, chore);
  }
}
