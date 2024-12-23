import {
  IsBoolean,
  IsDate,
  IsInt,
  IsOptional,
  IsPositive,
  IsString,
  Length,
  MaxLength,
} from 'class-validator';

export class UpdateChoreDTO {
  @IsString()
  @Length(1, 50)
  title!: string;

  @IsOptional()
  @IsString()
  @MaxLength(255)
  description!: string;

  @IsDate()
  deadline!: Date;

  @IsInt()
  @IsPositive()
  priorityLevel!: number;

  @IsBoolean()
  done!: boolean;
}
