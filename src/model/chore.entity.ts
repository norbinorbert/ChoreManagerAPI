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

  @Column('date')
  deadline!: Date;

  @Column('int')
  priorityLevel!: number;

  @Column('bool')
  done!: boolean;

  @OneToMany(() => Subtask, subtask => subtask.chore, {
    cascade: true,
    orphanedRowAction: 'delete',
  })
  subtasks!: Subtask[];

  toJSON() {
    const { subtasks, ...rest } = this;
    return {
      ...rest,
      subtasks: subtasks?.map(subtask => ({
        ...subtask,
        chore: undefined,
      })),
    };
  }
}
