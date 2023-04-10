import axios from 'axios';
import { TestURI } from '../../utility/uri';

const uri = TestURI;

const fetchAuth = async (fetchData) => {
  const method = fetchData.method;
  const url = fetchData.url;
  const data = fetchData.data;
  const header = fetchData.header;

  try {
    const response =
      (method === 'get' && (await axios.get(uri + url, header))) ||
      (method === 'post' && (await axios.post(uri + url, data, header))) ||
      (method === 'put' && (await axios.put(uri + url, data, header))) ||
      (method === 'delete' && (await axios.delete(uri + url, header)));
    if (response && response.data.error) {
      alert('Wrong ID or Password');
      return null;
    }

    if (!response) {
      alert('false!');
      return null;
    }

    return response;
  } catch (err) {
    window.alert(err.response.data);

    if (axios.isAxiosError(err)) {
      const serverError = err;
      if (serverError && serverError.response) {
        return null;
      }
    }
    return null;
  }
};

const GET = (url, header) => {
  const response = fetchAuth({ method: 'get', url, header });
  return response;
};

const POST = (url, data, header) => {
  const response = fetchAuth({ method: 'post', url, data, header });
  return response;
};

const PUT = async (url, data, header) => {
  const response = fetchAuth({ method: 'put', url, data, header });
  return response;
};

const DELETE = async (url, header) => {
  const response = fetchAuth({ method: 'delete', url, header });
  return response;
};

export { GET, POST, PUT, DELETE };
