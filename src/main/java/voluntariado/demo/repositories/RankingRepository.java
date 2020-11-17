package voluntariado.demo.repositories;

import voluntariado.demo.models.Ranking;

import java.util.List;

public interface RankingRepository {
    public List<Ranking> getAllRanking();
    public Ranking createRanking(Ranking ranking);

    void deleteRankingById(Integer id);

    Ranking getRankingById(Integer id);

    Ranking updateRankingById(Integer id, Ranking ranking);
}
