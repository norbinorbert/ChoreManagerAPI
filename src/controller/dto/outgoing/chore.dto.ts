import { Transform } from 'class-transformer';
import { Subtask } from '../../../model/subtask.entity';

export class ChoreDTO {
  id!: number;
  title!: string;
  description!: string;
  @Transform(({ value }) => value.toISOString().split('T')[0])
  deadline!: Date;
  priorityLevel!: number;
  done!: boolean;
  subtasks!: Subtask[];
}
