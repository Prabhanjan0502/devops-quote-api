import os
import random
from flask import Flask, jsonify, request

app = Flask(__name__)

quotes = [
    "Automate everything you do twice.",
    "Small commits, frequent merges.",
    "If it is not in version control, it does not exist.",
]


@app.route("/")
def home():
    return jsonify(message="Quote API is running")


@app.route("/health")
def health():
    return jsonify(status="ok")


@app.route("/quote", methods=["GET"])
def random_quote():
    return jsonify(quote=random.choice(quotes))


@app.route("/quotes", methods=["GET"])
def list_quotes():
    return jsonify(quotes=quotes)


@app.route("/quotes", methods=["POST"])
def add_quote():
    data = request.get_json(silent=True) or {}
    text = data.get("quote")
    if not text:
        return jsonify(error="Field 'quote' is required"), 400
    quotes.append(text)
    return jsonify(quote=text), 201


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=int(os.environ.get("PORT", 5000)))
