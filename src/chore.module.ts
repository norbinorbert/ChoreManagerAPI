import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { ChoreService } from './service/chore.service';
import { ChoreController } from './controller/chore.controller';
import { CombinedController } from './controller/combined.controller';
import { Chore } from './model/chore.entity';
import { Subtask } from './model/subtask.entity';

@Module({
  imports: [TypeOrmModule.forFeature([Chore, Subtask])],
  controllers: [ChoreController, CombinedController],
  providers: [ChoreService],
})
export class ChoreModule {}
