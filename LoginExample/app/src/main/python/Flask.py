# import main Flask class and request object
from flask import Flask, request

# create the Flask app
app = Flask(__name__)

@app.route('/login',  methods=['POST'])
def login():
    request_data = request.get_json()
    username = request_data['username']
    password = request_data['password']

    # Do something to validate user and password
    return f'{username, password}'

@app.route('/query-example')
def query_example():
    language = request.args.get('language')

    framework = request.args['framework']

    website = request.args.get('website')

    return '''
              <h1>The language value is: {}</h1>
              <h1>The framework value is: {}</h1>
              <h1>The website value is: {}'''.format(language, framework, website)

@app.route('/form-example', methods=['POST'])
def form_example():
    if request.method == 'POST':
        language = request.form.get('language')
        framework = request.form.get('framework')
        return '''
              <h1>The language value is: {}</h1>
              <h1>The framework value is: {}</h1>'''.format(language, framework)
    return ' GET'

@app.route('/json-example', methods=['POST'])
def json_example():
    request_data = request.get_json()
    language = request_data['language']
    framework = request_data['framework']

    python_version = request_data['version_info']['python']

    example = request_data['examples'][0]

    boolean_test = request_data['boolean_test']

    return '''
           The language value is: {}
           The framework value is: {}
           The Python version is: {}
           The item at index 0 in the example list is: {}
           The boolean value is: {}'''.format(language, framework,
                                              python_version, example, boolean_test)

if __name__ == '__main__':
    # run app in debug mode on port 5000
    app.run('0.0.0.0', debug=True, port=5000)

