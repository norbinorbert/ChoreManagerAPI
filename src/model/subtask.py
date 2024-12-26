from sqlalchemy.orm import relationship

from src.model.base_entity import BaseEntity
from src.db import db


class Subtask(BaseEntity):
    name = db.Column(db.String(255), nullable=False)
    choreId = db.Column(db.Integer, db.ForeignKey('chore.id'), nullable=False)
    chore = relationship('Chore', back_populates='subtasks')
