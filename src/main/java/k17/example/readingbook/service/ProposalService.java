package k17.example.readingbook.service;

import k17.example.readingbook.entity.Proposal;
import k17.example.readingbook.model.dto.PropPagingDto;
import k17.example.readingbook.model.request.ParamProp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProposalService {
    Proposal getPropById(int id);
    List<Proposal> getAllProp();
    void deletePropById(int id);
    Proposal updatePropById(ParamProp param, int id);
    Proposal createProp(ParamProp param);
    PropPagingDto getAllPropPaging(int pageNumber);
}
