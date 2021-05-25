package io.github.pengdst.jetpacksubmission.utils

import com.google.gson.Gson
import io.github.pengdst.jetpacksubmission.data.source.domain.models.Movie
import io.github.pengdst.jetpacksubmission.data.source.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.data.source.remote.mapper.MovieMapper.toDomain
import io.github.pengdst.jetpacksubmission.data.source.remote.mapper.TvMapper.toDomain
import io.github.pengdst.jetpacksubmission.data.source.remote.models.MovieDto
import io.github.pengdst.jetpacksubmission.data.source.remote.models.TvDto
import io.github.pengdst.jetpacksubmission.data.source.remote.models.relations.GenreDto
import io.github.pengdst.jetpacksubmission.data.source.remote.response.MovieResponse
import io.github.pengdst.jetpacksubmission.data.source.remote.response.TvResponse

/**
 * Created on 5/11/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
object DataStore {

    const val TYPE_MOVIE = "movie"
    const val TYPE_TV_SHOW = "tv_show"

    const val moviesUpcomingResponseBody = """{
  "dates": {
    "maximum": "2021-06-16",
    "minimum": "2021-05-26"
  },
  "page": 1,
  "results": [
    {
      "adult": false,
      "backdrop_path": "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
      "genre_ids": [
        878,
        28,
        18
      ],
      "id": 399566,
      "original_language": "en",
      "original_title": "Godzilla vs. Kong",
      "overview": "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
      "popularity": 1564.627,
      "poster_path": "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
      "release_date": "2021-03-24",
      "title": "Godzilla vs. Kong",
      "video": false,
      "vote_average": 8.1,
      "vote_count": 5549
    },
    {
      "adult": false,
      "backdrop_path": "/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg",
      "genre_ids": [
        28,
        53,
        80,
        35
      ],
      "id": 615457,
      "original_language": "en",
      "original_title": "Nobody",
      "overview": "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \"nobody.\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
      "popularity": 1196.803,
      "poster_path": "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
      "release_date": "2021-03-26",
      "title": "Nobody",
      "video": false,
      "vote_average": 8.4,
      "vote_count": 1523
    },
    {
      "adult": false,
      "backdrop_path": "/lkInRiMtLgl9u9xE0By5hqf66K8.jpg",
      "genre_ids": [
        27
      ],
      "id": 632357,
      "original_language": "en",
      "original_title": "The Unholy",
      "overview": "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
      "popularity": 1132.929,
      "poster_path": "/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
      "release_date": "2021-03-31",
      "title": "The Unholy",
      "video": false,
      "vote_average": 5.6,
      "vote_count": 89
    },
    {
      "adult": false,
      "backdrop_path": "/xPpXYnCWfjkt3zzE0dpCNME1pXF.jpg",
      "genre_ids": [
        16,
        28,
        12,
        14,
        18
      ],
      "id": 635302,
      "original_language": "ja",
      "original_title": "劇場版「鬼滅の刃」無限列車編",
      "overview": "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
      "popularity": 1010.159,
      "poster_path": "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
      "release_date": "2020-10-16",
      "title": "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
      "video": false,
      "vote_average": 8.4,
      "vote_count": 958
    },
    {
      "adult": false,
      "backdrop_path": "/mYM8x2Atv4MaLulaV0KVJWI1Djv.jpg",
      "genre_ids": [
        28,
        80,
        53
      ],
      "id": 804435,
      "original_language": "en",
      "original_title": "Vanquish",
      "overview": "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
      "popularity": 809.144,
      "poster_path": "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
      "release_date": "2021-04-16",
      "title": "Vanquish",
      "video": false,
      "vote_average": 6.3,
      "vote_count": 90
    },
    {
      "adult": false,
      "backdrop_path": "/7HtvmsLrDeiAgDGa1W3m6senpfE.jpg",
      "genre_ids": [
        12,
        16,
        10751
      ],
      "id": 681260,
      "original_language": "en",
      "original_title": "Maya the Bee: The Golden Orb",
      "overview": "When Maya, a headstrong little bee, and her best friend Willi, rescue an ant princess they find themselves in the middle of an epic bug battle that will take them to strange new worlds and test their friendship to its limits.",
      "popularity": 763.168,
      "poster_path": "/tMS2qcbhbkFpcwLnbUE9o9IK4HH.jpg",
      "release_date": "2021-01-07",
      "title": "Maya the Bee: The Golden Orb",
      "video": false,
      "vote_average": 6.9,
      "vote_count": 28
    },
    {
      "adult": false,
      "backdrop_path": "/ouOojiypBE6CD1aqcHPVq7cJf2R.jpg",
      "genre_ids": [
        53,
        18,
        28,
        9648
      ],
      "id": 578701,
      "original_language": "en",
      "original_title": "Those Who Wish Me Dead",
      "overview": "A young boy finds himself pursued by two assassins in the Montana wilderness with a survival expert determined to protecting him - and a forest fire threatening to consume them all.",
      "popularity": 738.115,
      "poster_path": "/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg",
      "release_date": "2021-05-05",
      "title": "Those Who Wish Me Dead",
      "video": false,
      "vote_average": 7.2,
      "vote_count": 172
    },
    {
      "adult": false,
      "backdrop_path": "/ovggmAOu1IbPGTQE8lg4lBasNC7.jpg",
      "genre_ids": [
        878,
        28,
        12,
        53
      ],
      "id": 412656,
      "original_language": "en",
      "original_title": "Chaos Walking",
      "overview": "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone.",
      "popularity": 557.859,
      "poster_path": "/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
      "release_date": "2021-02-24",
      "title": "Chaos Walking",
      "video": false,
      "vote_average": 7.1,
      "vote_count": 594
    },
    {
      "adult": false,
      "backdrop_path": "/9ns9463dwOeo1CK1JU2wirL5Yi1.jpg",
      "genre_ids": [
        35,
        10751,
        16
      ],
      "id": 587807,
      "original_language": "en",
      "original_title": "Tom & Jerry",
      "overview": "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.",
      "popularity": 476.296,
      "poster_path": "/8XZI9QZ7Pm3fVkigWJPbrXCMzjq.jpg",
      "release_date": "2021-02-11",
      "title": "Tom & Jerry",
      "video": false,
      "vote_average": 7.3,
      "vote_count": 1359
    },
    {
      "adult": false,
      "backdrop_path": "/kf456ZqeC45XTvo6W9pW5clYKfQ.jpg",
      "genre_ids": [
        10751,
        16,
        35,
        18,
        10402,
        14
      ],
      "id": 508442,
      "original_language": "en",
      "original_title": "Soul",
      "overview": "Joe Gardner is a middle school teacher with a love for jazz music. After a successful gig at the Half Note Club, he suddenly gets into an accident that separates his soul from his body and is transported to the You Seminar, a center in which souls develop and gain passions before being transported to a newborn child. Joe must enlist help from the other souls-in-training, like 22, a soul who has spent eons in the You Seminar, in order to get back to Earth.",
      "popularity": 418.784,
      "poster_path": "/hm58Jw4Lw8OIeECIq5qyPYhAeRJ.jpg",
      "release_date": "2020-12-25",
      "title": "Soul",
      "video": false,
      "vote_average": 8.3,
      "vote_count": 6026
    }
  ],
  "total_pages": 15,
  "total_results": 290
}"""

    const val tvOnAirResponseBody = """{
  "page": 1,
  "results": [
    {
      "backdrop_path": "/jeruqNWhqRqOR1QyqdQdHunrvU5.jpg",
      "first_air_date": "2014-10-07",
      "genre_ids": [
        18,
        10765
      ],
      "id": 60735,
      "name": "The Flash",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "The Flash",
      "overview": "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
      "popularity": 1135.315,
      "poster_path": "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
      "vote_average": 7.7,
      "vote_count": 7632
    },
    {
      "backdrop_path": "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
      "first_air_date": "2017-09-25",
      "genre_ids": [
        18
      ],
      "id": 71712,
      "name": "The Good Doctor",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "The Good Doctor",
      "overview": "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
      "popularity": 1087.369,
      "poster_path": "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
      "vote_average": 8.6,
      "vote_count": 8413
    },
    {
      "backdrop_path": "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
      "first_air_date": "2005-03-27",
      "genre_ids": [
        18
      ],
      "id": 1416,
      "name": "Grey's Anatomy",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Grey's Anatomy",
      "overview": "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
      "popularity": 770.248,
      "poster_path": "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
      "vote_average": 8.2,
      "vote_count": 6063
    },
    {
      "backdrop_path": "/wkyzeBBKLhSg1Oqhky5yoiFF2hG.jpg",
      "first_air_date": "2018-04-22",
      "genre_ids": [
        18
      ],
      "id": 79008,
      "name": "Luis Miguel: The Series",
      "origin_country": [
        "MX"
      ],
      "original_language": "es",
      "original_name": "Luis Miguel: La Serie",
      "overview": "The series dramatizes the life story of Mexican superstar singer Luis Miguel, who has captivated audiences in Latin America and beyond for decades.",
      "popularity": 627.121,
      "poster_path": "/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg",
      "vote_average": 8.1,
      "vote_count": 411
    },
    {
      "backdrop_path": "/sjxtIUCWR74yPPcZFfTsToepfWm.jpg",
      "first_air_date": "2021-05-04",
      "genre_ids": [
        10765,
        10759,
        16
      ],
      "id": 105971,
      "name": "The Bad Batch",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "The Bad Batch",
      "overview": "Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars.",
      "popularity": 603.928,
      "poster_path": "/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg",
      "vote_average": 9,
      "vote_count": 179
    },
    {
      "backdrop_path": "/pPKiIJEEcV0E1hpVcWRXyp73ZpX.jpg",
      "first_air_date": "2021-02-23",
      "genre_ids": [
        10759,
        10765,
        18
      ],
      "id": 95057,
      "name": "Superman & Lois",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Superman & Lois",
      "overview": "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.",
      "popularity": 525.307,
      "poster_path": "/vlv1gn98GqMnKHLSh0dNciqGfBl.jpg",
      "vote_average": 8.3,
      "vote_count": 827
    },
    {
      "backdrop_path": "/hNiGqLsiD30C194lci7VYDmciHD.jpg",
      "first_air_date": "2017-04-26",
      "genre_ids": [
        10765,
        18
      ],
      "id": 69478,
      "name": "The Handmaid's Tale",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "The Handmaid's Tale",
      "overview": "Set in a dystopian future, a woman is forced to live as a concubine under a fundamentalist theocratic dictatorship. A TV adaptation of Margaret Atwood's novel.",
      "popularity": 494.192,
      "poster_path": "/oIkxqt6ug5zT5ZSUUyc1Iqopf02.jpg",
      "vote_average": 8.2,
      "vote_count": 1338
    },
    {
      "backdrop_path": "/pnyT1foDmmXTsho2DfxN2ePI8ix.jpg",
      "first_air_date": "2018-06-12",
      "genre_ids": [
        18
      ],
      "id": 80240,
      "name": "The Queen of Flow",
      "origin_country": [
        "CO"
      ],
      "original_language": "es",
      "original_name": "La Reina del Flow",
      "overview": "After spending seventeen years in prison unfairly, a talented songwriter seeks revenge on the men who sank her and killed her family.",
      "popularity": 487.047,
      "poster_path": "/fuVuDYrs8sxvEolnYr0wCSvtyTi.jpg",
      "vote_average": 8,
      "vote_count": 759
    },
    {
      "backdrop_path": "/5VltHQJXdmbSD6gEJw3R8R1Kbmc.jpg",
      "first_air_date": "2016-09-23",
      "genre_ids": [
        9648,
        10765,
        10759
      ],
      "id": 65820,
      "name": "Van Helsing",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Van Helsing",
      "overview": "Vanessa Helsing, the daughter of famous vampire hunter and Dracula nemesis Abraham Van Helsing is resurrected five years in the future to find out that vampires have taken over the world and that she possesses unique power over them. She is humanity’s last hope to lead an offensive to take back what has been lost.",
      "popularity": 425.894,
      "poster_path": "/r8ODGmfNbZQlNhiJl2xQENE2jsk.jpg",
      "vote_average": 6.9,
      "vote_count": 556
    },
    {
      "backdrop_path": "/fRYwdeNjMqC30EhofPx5PlDpdun.jpg",
      "first_air_date": "2018-10-25",
      "genre_ids": [
        10765,
        18
      ],
      "id": 79460,
      "name": "Legacies",
      "origin_country": [
        "US"
      ],
      "original_language": "en",
      "original_name": "Legacies",
      "overview": "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted.",
      "popularity": 407.163,
      "poster_path": "/qTZIgXrBKURBK1KrsT7fe3qwtl9.jpg",
      "vote_average": 8.6,
      "vote_count": 1869
    }
  ],
  "total_pages": 47,
  "total_results": 934
}"""

    val moviesResponse: List<MovieDto> = Gson().fromJson(moviesUpcomingResponseBody, MovieResponse::class.java).results!!

    val tvShowListResponse: List<TvDto> = Gson().fromJson(DataStore.tvOnAirResponseBody, TvResponse::class.java).results!!

    val movies: List<Movie> = moviesResponse.toDomain()

    val tvShowList: List<TvShow> = tvShowListResponse.toDomain()

    val movieResponseBody: String = Gson().toJson(moviesResponse[0])
    val tvShowResponseBody: String = Gson().toJson(tvShowListResponse[0])

}