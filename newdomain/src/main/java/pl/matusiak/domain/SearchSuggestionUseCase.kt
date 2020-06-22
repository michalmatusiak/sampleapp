package pl.matusiak.domain

import pl.matusiak.repository.MovieRemoteRepository

class SearchSuggestionUseCase(private val repository: MovieRemoteRepository) {

    fun search(searchText: String) =
        repository.getSearchSuggestion(searchText)
}