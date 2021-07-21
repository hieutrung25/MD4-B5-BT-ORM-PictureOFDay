package service.picture;

import model.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import repositories.PictureRepository;

import java.util.LinkedList;
import java.util.List;

@Service
public class PictureServiceImp implements PictureService {
    private PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImp(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<Picture> findAll() {
        Iterable<Picture> iterable = pictureRepository.findAll();
        List<Picture> pictures = new LinkedList<>();
        for (Picture picture : iterable) {
            pictures.add(picture);
        }
        return pictures;
    }

    @Override
    public List<Picture> findAll(Sort sort) {
        Iterable<Picture> iterable = pictureRepository.findAll(sort);
        List<Picture> pictures = new LinkedList<>();
        for (Picture picture : iterable) {
            pictures.add(picture);
        }
        return pictures;
    }

    @Override
    public Page<Picture> findAll(Pageable pageable) {
        return pictureRepository.findAll(pageable);
    }

    @Override
    public Picture findOne(Long id) {
        return pictureRepository.findById(id).orElse(null);
    }

    @Override
    public Picture save(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public void delete(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public void delete(Long id) {
        pictureRepository.deleteById(id);
    }
}
