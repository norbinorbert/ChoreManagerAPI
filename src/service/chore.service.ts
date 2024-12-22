import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Chore } from '../model/chore.entity';

@Injectable()
export class ChoreService {
  constructor(
    @InjectRepository(Chore)
    private readonly choreRepository: Repository<Chore>
  ) {}

  async create(choreData: Partial<Chore>): Promise<number> {
    const chore = this.choreRepository.create(choreData);
    return (await this.choreRepository.save(chore)).id;
  }

  async findAll(done?: boolean): Promise<Chore[]> {
    if (done !== undefined) {
      return this.choreRepository.find({
        where: { done },
      });
    }
    return this.choreRepository.find();
  }

  async findById(id: number): Promise<Chore> {
    const chore = await this.choreRepository.findOne({
      where: { id },
    });
    if (!chore) {
      throw new NotFoundException('Chore not found');
    }
    return chore;
  }

  async update(id: number, choreData: Partial<Chore>): Promise<void> {
    let chore = await this.findById(id);
    chore = Object.assign(chore, choreData);
    this.choreRepository.save(chore);
  }

  async delete(id: number): Promise<void> {
    await this.choreRepository.delete(id);
  }
}
