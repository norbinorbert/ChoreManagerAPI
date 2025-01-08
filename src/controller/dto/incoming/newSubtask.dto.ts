import { IsString, Length } from 'class-validator';

export class NewSubtaskDTO {
  @IsString()
  @Length(1, 255)
  name!: string;
}
