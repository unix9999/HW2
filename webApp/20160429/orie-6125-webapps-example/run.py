import os
from app import app

port = int(os.environ.get('PORT'))
app.run(host='0.0.0.0', port=port)
