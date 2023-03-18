import com.github.jacekszymanski.realcamel.RouteException

String token = request.headers['Authorization']

def spl = token.split(' ')

if (spl.length != 2) {
  throw new RouteException("malformed token")
}

exchange.properties['jwt_token'] = [
    username: spl[1],
    raw_token: token
]
