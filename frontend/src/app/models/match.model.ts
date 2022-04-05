import { Team } from "./team.model"
import { Tournament } from "./tournament.model"

export interface Match {

  id?: number
  round: number
  result: number[]
  setsTeamOne: number
  setsTeamTwo: number
  hasWinner: boolean
  winnerTeamOne: boolean
  winnerTeamTwo: boolean
  teamOne: Team
  teamTwo: Team
  tournament: Tournament



 
}
