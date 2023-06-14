export interface Hero {
    name: string,
    role: string,  // header
    good_against: string[],
    bad_against: string[],
    combo: string[],
    meta: number,  //header
    player_skills: number,  //descr
    rank_play: string,  //header
    id: number,
    localized_name: string, //header
    score: number  //header
    image: string,
}