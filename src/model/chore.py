from sqlalchemy.orm import relationship

from src.model.base_entity import BaseEntity
from src.db import db


class Chore(BaseEntity):
    title = db.Column(db.String(50), nullable=False)
    description = db.Column(db.String(255))
    deadline = db.Column(db.Date, nullable=False)
    priorityLevel = db.Column(db.Integer, nullable=False)
    done = db.Column(db.Boolean, default=False, nullable=False)
    subtasks = relationship('Subtask', back_populates='chore', cascade='all, delete-orphan')
