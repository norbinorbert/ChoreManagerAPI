import {
  Controller,
  Get,
  Post,
  Patch,
  Delete,
  Param,
  Query,
  Body,
  HttpCode,
  HttpStatus,
  UsePipes,
  ValidationPipe,
} from '@nestjs/common';
import { ChoreService } from '../service/chore.service';
import { NoDescriptionChoreDTO } from './dto/outgoing/noDescriptionChore.dto';
import { NoIdChoreDTO } from './dto/outgoing/noIdChore.dto';
import { ChoreDTO } from './dto/outgoing/chore.dto';
import { NewChoreDTO } from './dto/incoming/newChore.dto';
import { UpdateChoreDTO } from './dto/incoming/updateChore.dto';

@Controller('chores')
export class ChoreController {
  constructor(private readonly choreService: ChoreService) {}

  @Get()
  async findAll(
    @Query('done') done?: string
  ): Promise<NoDescriptionChoreDTO[]> {
    return this.choreService.findAll(done ? done === 'true' : undefined);
  }

  @Get(':id')
  async findById(@Param('id') id: number): Promise<NoIdChoreDTO> {
    return this.choreService.findById(id);
  }

  @Post()
  @HttpCode(HttpStatus.CREATED)
  @UsePipes(new ValidationPipe({ transform: true }))
  async create(@Body() choreData: NewChoreDTO): Promise<ChoreDTO> {
    const id = await this.choreService.create(choreData);
    return { ...choreData, id, done: false } as ChoreDTO;
  }

  @Patch(':id')
  @HttpCode(HttpStatus.NO_CONTENT)
  @UsePipes(new ValidationPipe({ transform: true }))
  async update(
    @Param('id') id: number,
    @Body() choreData: UpdateChoreDTO
  ): Promise<void> {
    await this.choreService.update(id, choreData);
  }

  @Delete(':id')
  @HttpCode(HttpStatus.NO_CONTENT)
  async delete(@Param('id') id: number): Promise<void> {
    await this.choreService.delete(id);
  }
}
