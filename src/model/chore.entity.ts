import { Entity, PrimaryGeneratedColumn, Column, OneToMany } from 'typeorm';
import { Subtask } from './subtask.entity';

@Entity()
export class Chore {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column({ length: 50 })
  title!: string;

  @Column({ nullable: true })
  description!: string;

  @Column()
  deadline!: Date;

  @Column('int')
  priorityLevel!: number;

  @Column()
  done!: boolean;

  @OneToMany(() => Subtask, subtask => subtask.chore, { cascade: true })
  subtasks!: Subtask[];
}
