import os

from dotenv import load_dotenv
from flask import Flask
from sqlalchemy import URL

from src.controller.chore_controller import chore_bp
from src.controller.combined_controller import combined_bp
from src.controller.error_handler import register_error_handlers
from src.db import db

load_dotenv()
url_object = URL.create(
    drivername='mysql',
    username=os.getenv('DB_USER'),
    password=os.getenv('DB_PASS'),
    host=os.getenv('DB_HOST'),
    port=os.getenv('DB_PORT'),
    database=os.getenv('DB_NAME'))

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = url_object
db.init_app(app)

app.register_blueprint(chore_bp, url_prefix='/chores', propagate_exceptions=True)
app.register_blueprint(combined_bp, url_prefix='/chores/<int:chore_id>/subtasks', propagate_exceptions=True)
register_error_handlers(app)

if __name__ == '__main__':
    with app.app_context():
        db.create_all()
    app.run(debug=True, port=8080)
