import { Transform } from 'class-transformer';

export class NoIdChoreDTO {
  title!: string;
  description!: string;
  @Transform(({ value }) => value.toISOString().split('T')[0])
  deadline!: Date;
  priorityLevel!: number;
  done!: boolean;
}
