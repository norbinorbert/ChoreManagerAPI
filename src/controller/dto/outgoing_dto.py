from marshmallow import Schema, fields


class ChoreDTO(Schema):
    id = fields.Int(dump_only=True)
    title = fields.Str()
    description = fields.Str()
    deadline = fields.Date()
    priorityLevel = fields.Int()
    done = fields.Bool()
    subtasks = fields.List(fields.Nested('SubtaskDTO'))


chore_schema = ChoreDTO()


class NoDescriptionChoreDTO(Schema):
    id = fields.Int(dump_only=True)
    title = fields.Str()
    deadline = fields.Date()
    priorityLevel = fields.Int()
    done = fields.Bool()


no_description_chore_schema = NoDescriptionChoreDTO()


class NoIdChoreDTO(Schema):
    title = fields.Str()
    description = fields.Str()
    deadline = fields.Date()
    priorityLevel = fields.Int()
    done = fields.Bool()


no_id_chore_schema = NoIdChoreDTO()


class SubtaskDTO(Schema):
    id = fields.Int(dump_only=True)
    name = fields.Str()


subtask_schema = SubtaskDTO()
