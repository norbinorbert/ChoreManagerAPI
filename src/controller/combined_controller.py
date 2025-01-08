from flask import Blueprint, request, jsonify, abort
from marshmallow import ValidationError

from src.controller.dto.incoming_dto import new_subtask_schema
from src.controller.dto.outgoing_dto import subtask_schema, chore_schema
from src.service.chore_service import get_chore_by_id, add_subtask_to_chore, remove_subtask_from_chore
from src.service.exceptions import ChoreNotFoundException

combined_bp = Blueprint('subtask', __name__)


@combined_bp.route('', methods=['GET'])
def get_all(chore_id):
    try:
        chore = get_chore_by_id(chore_id)
        return jsonify(subtask_schema.dump(chore.subtasks, many=True))
    except ChoreNotFoundException:
        abort(404, 'Chore not found')


@combined_bp.route('', methods=['POST'])
def create(chore_id):
    try:
        subtask_data = new_subtask_schema.load(request.json)
        chore = add_subtask_to_chore(chore_id, subtask_data)
        return jsonify(chore_schema.dump(chore))
    except ValidationError as err:
        abort(400, err.messages)


@combined_bp.route('/<int:subtask_id>', methods=['DELETE'])
def delete(chore_id, subtask_id):
    remove_subtask_from_chore(chore_id, subtask_id)
    return '', 200
