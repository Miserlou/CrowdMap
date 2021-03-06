import string,cgi,time
import sqlite3
from os import curdir, sep
from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer

class MyHandler(BaseHTTPRequestHandler):

    def do_GET(self):
        try:
            self.send_response(200)
            self.send_header('Content-type',	'text/html')
            self.end_headers()
            self.wfile.write("Nuthin' here to see")
            return
        except IOError:
            self.send_error(404,'File Not Found: %s' % self.path)

    def do_POST(self):
        global rootnode
        try:
            # http://blog.doughellmann.com/2007/12/pymotw-basehttpserver.html
            # Parse the form data posted
            form = cgi.FieldStorage(
                fp=self.rfile,
                headers=self.headers,
                environ={'REQUEST_METHOD':'POST',
                'CONTENT_TYPE':self.headers['Content-Type'],
            })

            self.send_response(301)
            self.end_headers()

            point = {}
            for field in form.keys():
                point[field] = str(form[field].value)

            self.conn = sqlite3.connect('crowdmap.sqlite')
            c = self.conn.cursor()
            query = "insert into points values (?,?,?,?,?,?,?,?)"

            c.execute(query, (point['ip'], point['connection_type'], point['carrier'],
                point['latitude'], point['longitude'], point['location_type'],
                point['time'], point['unique_id']))

            self.conn.commit()
            c.close()
            print "Added to database: "
            print point

        except Exception, e:
            print e
            print "Woah, we've gota derp."
            pass

def main():
    try:
        server = HTTPServer(('', 8888), MyHandler)
        print 'started httpserver...'
        server.serve_forever()
    except KeyboardInterrupt:
        print '^C received, shutting down server'
        server.socket.close()

if __name__ == '__main__':
    main()

