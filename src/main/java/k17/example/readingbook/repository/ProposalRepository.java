package k17.example.readingbook.repository;

import k17.example.readingbook.entity.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProposalRepository extends JpaRepository<Proposal,Integer> {
    Proposal findByPropId(int id);
    List<Proposal> findAllBy();
}
