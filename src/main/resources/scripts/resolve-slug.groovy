def slug = request.headers.'slug'

if (slug.matches('\\d+$')) {
  result = Long.parseLong(slug)
}
else if (slug.matches('.*-\\d+$')) {
  result = slug.replaceFirst('.*-(\\d+)$', '$1')
}
else {
  throw new com.github.jacekszymanski.realcamel.RouteException("Invalid slug: ${slug}")
}
