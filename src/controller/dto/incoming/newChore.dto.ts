import { Type } from 'class-transformer';
import {
  IsDate,
  IsInt,
  IsOptional,
  IsPositive,
  IsString,
  Length,
  MaxLength,
  MinDate,
} from 'class-validator';

export class NewChoreDTO {
  @IsString()
  @Length(1, 50)
  title!: string;

  @IsOptional()
  @IsString()
  @MaxLength(255)
  description!: string;

  @IsDate()
  @MinDate(new Date())
  @Type(() => Date)
  deadline!: Date;

  @IsInt()
  @IsPositive()
  priorityLevel!: number;
}
