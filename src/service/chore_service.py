from src.model.chore import Chore
from src.db import db
from src.model.subtask import Subtask
from src.service.exceptions import ChoreNotFoundException


def get_all_chores(done=None):
    if done is not None:
        chores = Chore.query.filter_by(done=done.lower() == 'true').all()
    else:
        chores = Chore.query.all()
    return chores


def get_chore_by_id(chore_id):
    chore = Chore.query.get(chore_id)
    if not chore:
        raise ChoreNotFoundException
    return chore


def create_chore(data):
    chore = Chore(**data)
    db.session.add(chore)
    db.session.commit()
    return chore


def update_chore(chore_id, data):
    chore = Chore.query.get(chore_id)
    if not chore:
        raise ChoreNotFoundException
    for key, value in data.items():
        setattr(chore, key, value)
    db.session.commit()


def delete_chore(chore_id):
    chore = Chore.query.get(chore_id)
    if not chore:
        return
    db.session.delete(chore)
    db.session.commit()


def add_subtask_to_chore(chore_id, subtask_data):
    chore = Chore.query.get(chore_id)
    if not chore:
        raise ChoreNotFoundException

    subtask = Subtask(name=subtask_data.get('name'))
    chore.subtasks.append(subtask)

    db.session.commit()
    return chore


def remove_subtask_from_chore(chore_id, subtask_id):
    chore = Chore.query.get(chore_id)
    if not chore:
        raise ChoreNotFoundException

    subtask = Subtask.query.get(subtask_id)
    if not subtask or subtask not in chore.subtasks:
        return

    chore.subtasks.remove(subtask)
    db.session.delete(subtask)
    db.session.commit()
