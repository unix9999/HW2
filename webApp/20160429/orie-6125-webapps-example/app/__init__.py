from flask import Flask, render_template, request

app = Flask(__name__)

@app.route('/')
def home():
    return render_template('home.html')

@app.route('/sorting', methods=['GET'])
def sorting_get():
    return render_template('sorting.html')

@app.route('/sorting', methods=['POST'])
def sorting_post():
    els = request.form.get('values', None)

    if els is None:
        return 'No "values" parameter was provided', 400

    try:
        els = [int(e) for e in els.split(',')]
    except ValueError as e:
        return str(e), 400

    return ','.join(map(str, sorted(els)))

@app.route('/averaging', methods=['GET'])	
def averaging_get():
  return render_template('averaging.html')

@app.route('/averaging', methods=['POST'])  
def averaing_post():
    els = request.form.get('values', None)
    if els is None:
        return 'No "values" parameter was provided', 400
    try:
        els = [int(e) for e in els.split(',')]
    except ValueError as e:
        return str(e), 400
    return str( sum(els)/ len(els) )
  
  
