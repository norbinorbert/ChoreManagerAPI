from datetime import datetime

from marshmallow import Schema, fields, validate


class NewChoreDTO(Schema):
    title = fields.Str(required=True, validate=validate.Length(min=1, max=50))
    description = fields.Str(required=False, allow_none=True, validate=validate.Length(max=255))
    deadline = fields.Date(required=True, validate=lambda x: x >= datetime.now().date())
    priorityLevel = fields.Int(required=True, validate=validate.Range(min=1))


new_chore_schema = NewChoreDTO()


class UpdateChoreDTO(Schema):
    title = fields.Str(required=True, validate=validate.Length(min=1, max=50))
    description = fields.Str(required=False, allow_none=True, validate=validate.Length(max=255))
    deadline = fields.Date(required=True)
    priorityLevel = fields.Int(required=True, validate=validate.Range(min=1))
    done = fields.Bool(required=True)


update_chore_schema = UpdateChoreDTO()


class NewSubtaskDTO(Schema):
    name = fields.Str(required=True, validate=validate.Length(min=1, max=255))


new_subtask_schema = NewSubtaskDTO()
