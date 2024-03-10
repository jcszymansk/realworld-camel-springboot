{ slug ->
  if (slug.matches('\\d+$')) {
    Long.parseLong(slug)
  }
  else if (slug.matches('.*-\\d+$')) {
    slug.replaceFirst('.*-(\\d+)$', '$1')
  }
  else {
    throw new com.github.jacekszymanski.realcamel.RouteException("Invalid slug: ${slug}")
  }
}(request.headers.slug)
