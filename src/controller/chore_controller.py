from flask import Blueprint, request, jsonify, abort
from marshmallow import ValidationError

from src.controller.dto.incoming_dto import new_chore_schema, update_chore_schema
from src.controller.dto.outgoing_dto import no_id_chore_schema, chore_schema, no_description_chore_schema
from src.service.chore_service import get_all_chores, get_chore_by_id, create_chore, update_chore, delete_chore
from src.service.exceptions import ChoreNotFoundException

chore_bp = Blueprint('chore', __name__)


@chore_bp.route('', methods=['GET'])
def get_chores():
    return jsonify(no_description_chore_schema.dump(get_all_chores(request.args.get('done')), many=True))


@chore_bp.route('/<int:chore_id>', methods=['GET'])
def get_chore(chore_id):
    try:
        chore = get_chore_by_id(chore_id)
        return jsonify(no_id_chore_schema.dump(chore))
    except ChoreNotFoundException:
        abort(404, 'Chore not found')


@chore_bp.route('', methods=['POST'])
def create():
    try:
        chore_data = new_chore_schema.load(request.json)
        return jsonify(chore_schema.dump(create_chore(chore_data))), 201
    except ValidationError as err:
        abort(400, err.messages)


@chore_bp.route('/<int:chore_id>', methods=['PATCH'])
def update(chore_id):
    try:
        chore_data = update_chore_schema.load(request.json)
        update_chore(chore_id, chore_data)
        return '', 204
    except ValidationError as err:
        abort(400, err.messages)
    except ChoreNotFoundException:
        abort(404, 'Chore not found')


@chore_bp.route('/<int:chore_id>', methods=['DELETE'])
def delete(chore_id):
    delete_chore(chore_id)
    return '', 200
