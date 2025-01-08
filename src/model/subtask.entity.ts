import { Entity, PrimaryGeneratedColumn, Column, ManyToOne } from 'typeorm';
import { Chore } from './chore.entity';

@Entity()
export class Subtask {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column()
  name!: string;

  @ManyToOne(() => Chore, chore => chore.subtasks, {
    orphanedRowAction: 'delete',
    onDelete: 'CASCADE',
  })
  chore!: Chore;
}
