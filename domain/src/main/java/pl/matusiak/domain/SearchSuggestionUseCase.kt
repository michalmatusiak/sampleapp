package pl.matusiak.domain

import pl.matusiak.data.repository.MovieRepository

class SearchSuggestionUseCase(private val repository: MovieRepository) {

    fun search(searchText: String) =
        repository
            .getSearchSuggestion(searchText)
}