import { Transform } from 'class-transformer';

export class NoDescriptionChoreDTO {
  id!: number;
  title!: string;
  @Transform(({ value }) => value.toISOString().split('T')[0])
  deadline!: Date;
  priorityLevel!: number;
  done!: boolean;
}
