import { Subtask } from '../../../model/subtask.entity';

export class ChoreDTO {
  id!: number;
  title!: string;
  description!: string;
  deadline!: Date;
  priorityLevel!: number;
  done!: boolean;
  subtasks!: Subtask[];
}
