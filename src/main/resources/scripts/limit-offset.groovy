import com.github.jacekszymanski.realcamel.RouteException

def limit(limit) {
  switch(limit as Integer) {
    case null: return 10
    case { it <= 0 }: throw new RouteException("Limit must be greater than 0")
    case { it > 100 }: return 100
    default: return limit as Integer
  }
}

def offset(offset) {
  switch(offset as Integer) {
    case null: return 0
    case { it < 0 }: throw new RouteException("Offset must be greater than or equal to 0")
    default: return offset as Integer
  }
}
// limit and offset
request.headers['CamelXJpaMaximumResults'] = limit(request.headers.limit)
request.headers['CamelXJpaFirstResult'] = offset(request.headers.offset)
